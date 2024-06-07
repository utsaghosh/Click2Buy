import { useEffect } from "react";
import { useState } from "react";

export default function RatingViewCard({ itemRatings }) {
  const [ratingsView, setRatingsView] = useState({
    1: 0,
    2: 0,
    3: 0,
    4: 0,
    5: 0,
  });

  useEffect(() => {
    calculateRatings();
  }, [itemRatings]);

  const calculateRatings = () => {
    const countRatings = itemRatings.length;
    const counts = { 1: 0, 2: 0, 3: 0, 4: 0, 5: 0 };

    itemRatings?.forEach((rating) => {
      counts[rating.score] = counts[rating.score * 1] * 1 + 1;
    });

    for (const [key, value] of Object.entries(counts)) {
      counts[key] = (counts[key] * 100) / countRatings;
    }

    setRatingsView(counts);
  };

  return (
    <div className="container my-2">
      <div className="my-2">
        5 star :
        <div className="progress" style={{ width: "25%" }}>
          <div
            className="progress-bar"
            role="progressbar"
            style={{ width: `${ratingsView[5]}%` }}
            aria-valuenow={ratingsView[5]}
            aria-valuemin="0"
            aria-valuemax="100"
          >
            {ratingsView[5]}%
          </div>
        </div>
      </div>
      <div className="my-2">
        4 star :
        <div className="progress" style={{ width: "25%" }}>
          <div
            className="progress-bar"
            role="progressbar"
            style={{ width: `${ratingsView[4]}%` }}
            aria-valuenow={ratingsView[4]}
            aria-valuemin="0"
            aria-valuemax="100"
          >
            {ratingsView[4]}%
          </div>
        </div>
      </div>
      <div className="my-2">
        3 star :
        <div className="progress" style={{ width: "25%" }}>
          <div
            className="progress-bar"
            role="progressbar"
            style={{ width: `${ratingsView[3]}%` }}
            aria-valuenow={ratingsView[3]}
            aria-valuemin="0"
            aria-valuemax="100"
          >
            {ratingsView[3]}%
          </div>
        </div>
      </div>
      <div className="my-2">
        2 star :
        <div className="progress" style={{ width: "25%" }}>
          <div
            className="progress-bar"
            role="progressbar"
            style={{ width: `${ratingsView[2]}%` }}
            aria-valuenow={ratingsView[2]}
            aria-valuemin="0"
            aria-valuemax="100"
          >
            {ratingsView[2]}%
          </div>
        </div>
      </div>
      <div className="my-2">
        1 star :
        <div className="progress" style={{ width: "25%" }}>
          <div
            className="progress-bar"
            role="progressbar"
            style={{ width: `${ratingsView[1]}%` }}
            aria-valuenow={ratingsView[1]}
            aria-valuemin="0"
            aria-valuemax="100"
          >
            {ratingsView[1]}%
          </div>
        </div>
      </div>
    </div>
  );
}
